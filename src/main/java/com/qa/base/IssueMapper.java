package com.qa.base;


import com.deque.html.axecore.utilities.axeresults.*;


import java.util.List;


public class IssueMapper {


    public String browser;

        public String date;
        public String device;
        public String dimension;
        public String id;
        public String title;
    public List<Rule> inapplicable;
    public List<Rule> incomplete;
    public List<Rule> passes;
    public TestEngine testEngine;
    public TestEnvironment testEnvironment;
    public TestRunner testRunner;
    public String timeStamp;
    public ToolOptions toolOptions;
    public String url;
    public List<Rule> violations;

    public List<Rule> getInapplicable() {
        return inapplicable;
    }

    public void setInapplicable(List<Rule> inapplicable) {
        this.inapplicable = inapplicable;
    }

    public List<Rule> getIncomplete() {
        return incomplete;
    }

    public void setIncomplete(List<Rule> incomplete) {
        this.incomplete = incomplete;
    }

    public List<Rule> getPasses() {
        return passes;
    }

    public void setPasses(List<Rule> passes) {
        this.passes = passes;
    }

    public TestEngine getTestEngine() {
        return testEngine;
    }

    public void setTestEngine(TestEngine testEngine) {
        this.testEngine = testEngine;
    }

    public TestEnvironment getTestEnvironment() {
        return testEnvironment;
    }

    public void setTestEnvironment(TestEnvironment testEnvironment) {
        this.testEnvironment = testEnvironment;
    }

    public TestRunner getTestRunner() {
        return testRunner;
    }

    public void setTestRunner(TestRunner testRunner) {
        this.testRunner = testRunner;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ToolOptions getToolOptions() {
        return toolOptions;
    }

    public void setToolOptions(ToolOptions toolOptions) {
        this.toolOptions = toolOptions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Rule> getViolations() {
        return violations;
    }

    public void setViolations(List<Rule> violations) {
        this.violations = violations;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
