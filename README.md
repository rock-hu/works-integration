## works-integration
## works-integration
## What
## Why
## How
## Reuired Tool
1. Jdk 1.6
2. Spring Tool Suite
	EGit(Eclipse Market)/Propedit(http://propedit.sourceforge.jp/eclipse/updates/)
3. Maven 2
4. Git
5. Spring Batch 2.x
6. PostgreSQL
## Running locally		
1. Make sure project no compile error
2. mvn install to download dependency via Maven		
## Building and Deploying
1. Maven
	mvn clean install -Dmaven.test.skip=true -X > install.log			
	mvn dependency:copy-dependencies		
	mvn dependency:tree > tree.txt		
	mvn clean install -e -U		
	mvn clean install -Dmaven.test.skip=true -X > install.log		
	
	mvn dependency:analyze analyzes		
	mvn dependency:analyze-dep-mgt		
	mvn dependency:analyze-only		
	mvn dependency:analyze-report		
	mvn dependency:analyze-duplicate		
	mvn dependency:build-classpath		
	mvn dependency:copy		
	mvn dependency:copy-dependencies		
	mvn dependency:get		
	mvn dependency:go-offline		
	mvn dependency:list		
	mvn dependency:properties		
	mvn dependency:purge-local-repository		
	mvn dependency:resolve		
	mvn dependency:resolve-plugins		
	mvn dependency:sources		
	mvn dependency:tree > tree.txt		
	mvn dependency:unpack		
	mvn dependency:unpack-dependencies		
	
	checkstyle
	mvn checkstyle:help           查看checkstyle-plugin的帮助：		
	mvn checkstyle:help			    检查工程是否满足checkstyle的检查，如果没有满足，检查会失败，可以通过target/site/checkstyle.html查看。		
	mvn checkstyle:check		    检查工程是否满足checkstyle的检查，如果没有满足，检查不会失败，可以通过target/site/checkstyle.html查看。		
	mvn checkstyle:checkstyle-aggregate		检查工程是否满足checkstyle的检查，如果没有满足，检查不会失败，可以通过target/site/checkstyle.html查看。		
	mvn checkstyle:checkstyle		
	mvn jxr:jxr		
	
	cobertura		
	mvn cobertura:help          查看cobertura插件的帮助		  
	mvn cobertura:clean         清空cobertura插件运行结果  		
	mvn cobertura:check         运行cobertura的检查任务  		
	mvn cobertura:cobertura     运行cobertura的检查任务并生成报表，报表生成在target/site/cobertura目录下		  
	cobertura:dump-datafile     Cobertura Datafile Dump Mojo  		
	mvn cobertura:instrument    Instrument the compiled classes		
	
	findbugs		
	mvn findbugs:help       查看findbugs插件的帮助  		
	mvn findbugs:check      检查代码是否通过findbugs检查，如果没有通过检查，检查会失败，但检查不会生成结果报表		  
	mvn findbugs:findbugs   检查代码是否通过findbugs检查，如果没有通过检查，检查不会失败，会生成结果报表保存在target/findbugsXml.xml文件中		  
	mvn findbugs:gui        检查代码并启动gui界面来查看结果		
	
	mvn tattletale-maven:report		
	javadoc:aggregate 		
	schemaspy		
	

##	Development guidelines
1. Clone it (git clone https://github.com/rock-hu/works-integration.git)
2. Import project into your eclipse workspace
3. Write your code and unit tests
4. Ensure all tests still pass (grunt test)
5. Checking data change is expected
6. Commit & Push your changes			