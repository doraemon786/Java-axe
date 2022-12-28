package com.qa.testcases;
import com.deque.html.axecore.playwright.AxeBuilder;
import com.deque.html.axecore.utilities.axeresults.AxeResults;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qa.base.IssueMapper;
import com.qa.ftl.FtlConfig;
import com.qa.modal.axe.Accesskeys;
import com.qa.modal.axe.Issues;
import com.qa.modal.axe.RunOnly;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.microsoft.playwright.Page;
import com.qa.base.PlaywrightFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Files.*;
import static java.nio.file.Paths.get;

public class BaseTest {
	private static Logger LOG = LoggerFactory.getLogger(BaseTest.class);
	protected Page page;
	protected PlaywrightFactory play;

	private AxeBuilder axeBuilder ;

	@BeforeClass
	@Parameters({ "appURL", "browserType" })
	public void setUp(String appURL, String browserType) {
		play = new PlaywrightFactory();
		page = play.getPage(appURL, browserType);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		page.context().browser().close();
	}


	@AfterClass(alwaysRun = true)
	public void checkAccessibility() throws IOException {
		axeBuilder= new AxeBuilder(page);
		AxeResults analyze = axeBuilder.analyze();
		IssueMapper issueMapper=new IssueMapper();
		RunOnly runOnly=new RunOnly();
		Accesskeys accesskeys=new Accesskeys();
		accesskeys.setEnabled(false);
		List<String>  rules=new ArrayList<>();
		List<String>  resultTypes=new ArrayList<>();
		resultTypes.add("violations");
		resultTypes.add("incomplete");
		resultTypes.add("inapplicable");
		rules.add("wcag2a");
		rules.add("wcag2aa");
		rules.add("section508");
		runOnly.setType("Tag");
		runOnly.setValues(rules);
		issueMapper.setBrowser("Chrome 108.0.0.0");
		issueMapper.setDate(new Date().toString());
		issueMapper.setDevice("Small Laptop");
		issueMapper.setDimension("1036 X 674");
		issueMapper.setInapplicable(analyze.getInapplicable());
		issueMapper.setIncomplete(analyze.getIncomplete());
		issueMapper.setPasses(analyze.getPasses());
		issueMapper.setTestEngine(analyze.getTestEngine());
		issueMapper.setTestEnvironment(analyze.getTestEnvironment());
		issueMapper.setTestRunner(analyze.getTestRunner());
		issueMapper.setTimeStamp(analyze.getTimestamp());
		analyze.getToolOptions().setProperty("runOnly",runOnly);
		analyze.getToolOptions().setProperty("resultTypes",resultTypes);
		analyze.getToolOptions().setRules(accesskeys);
		issueMapper.setToolOptions(analyze.getToolOptions());
		issueMapper.setUrl(analyze.getUrl());
		issueMapper.setId(UUID.randomUUID().toString());
		issueMapper.setTitle("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
		issueMapper.setViolations(analyze.getViolations());
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(issueMapper);
		String engine="AXE";
		Path path = get("./target/java-a11y/" + engine.toString().toLowerCase() + "/json/" + UUID.randomUUID() + ".json");
		createDirectories(path.getParent());
		write(path, json.getBytes(StandardCharsets.UTF_8));

	}



	@AfterSuite
	public  void createFinalReport() throws IOException {
		String engine="AXE";
		List<Issues> issueList = jsonReports();

		Template tmplIndex = FtlConfig.getInstance().getTemplate(engine.toLowerCase() + "/index.ftl");
		Template tmplPage = FtlConfig.getInstance().getTemplate(engine.toLowerCase() + "/page.ftl");

		issueList.forEach(issues -> {
			String id = issues.getId();
			save(tmplPage, issues, id, engine);
		});

		Map<String, Object> root = new HashMap<>();
		root.put("list", issueList);
		root.put("title", "Accessibility Report");
		save(tmplIndex, root, "index", engine);
	}



	public void save(Template tmpl, Object map, String name, String engine) {
		Path path = null;
		File report = null;
		try {
			path = get("./target/java-a11y/" + engine.toLowerCase() + "/html");
			createDirectories(path);
			report = new File(path + File.separator + name + ".html");
			Writer file = new FileWriter(report);
			if (tmpl == null) {
				throw new IOException();
			}
			tmpl.process(map, file);
			file.flush();
			file.close();
			String loggerMsg = name.equalsIgnoreCase("index") ? "Consoliated " : "Page ";
			LOG.info(loggerMsg + "report generated at " + report.getAbsolutePath());
		} catch (IOException e) {
			LOG.error("unable to write file: " + path + File.separator + name);
			e.printStackTrace();
		} catch (TemplateException e) {
			LOG.error("unable to find template: " + tmpl + " for " + name);
			e.printStackTrace();
		}
	}

	public List<Issues> jsonReports() throws IOException {
		return walk(get("./target/java-a11y/" + "AXE".toLowerCase() + "/json/"))
				.filter(Files::isRegularFile)
				.map(Path::toFile)
				.filter(file -> FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("json"))
				.map(file -> {
					ObjectMapper mapper = new ObjectMapper();
					try {
						return mapper.readValue(file, Issues.class);
					} catch (IOException e) {
						e.printStackTrace();
						LOG.error("Failed to read json file {}", file.getAbsolutePath());
					}
					return null;
				})
				.collect(Collectors.toList());
	}
	
}
