package com.qa.testcases;
import com.deque.html.axecore.playwright.AxeBuilder;
import com.deque.html.axecore.utilities.axeresults.AxeResults;
import com.deque.html.axecore.utilities.axeresults.Rule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qa.ftl.FtlConfig;
import com.qa.base.IssueMapper;
import com.qa.modal.axe.Accesskeys;
import com.qa.modal.axe.Issues;
import com.qa.modal.axe.RunOnly;
import com.qa.pages.AmazonHomePage;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

public class HomePageTest extends BaseTest {

	private static Logger LOG = LoggerFactory.getLogger(HomePageTest.class);

    private AmazonHomePage  amazonHomePage;


	@BeforeClass
	public void init(){
    amazonHomePage=new AmazonHomePage(page);
	}

	@Test
	public void testHomePage() {

	}

}
