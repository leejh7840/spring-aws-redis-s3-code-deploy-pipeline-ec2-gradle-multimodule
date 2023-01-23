AWS RedisDB Multimodule Project 

This is a model project with sample implementation of RESTApis with AWS RedisDB and deploy in AWS Console.

Requirements
For building and running the application you need:

JDK 17<br />
Gradle 7.4<br />
Docker (Only needed if you want to run it on docker)<br />
AWS accesskey<br />
AWS secretkey<br />


Building
Gradle is the main tool for build & dependency management.<br />
You will be able to run gradle commands via the gradle wrapper in the root of this project, e.g. ./gradlew tasks

Enter your AWS region, accesskey and secretkey in spring-redis-aws-multimodule\src\main\resources\aws-credentials.properties.

./gradlew clean - Deletes the build directory.<br />
./gradlew build - Assembles and tests this project.<br />

Start application

./gradlew bootRun --> Starts the application on your local environment as a normal SpringBoot app.


-----
Dynamo DB table insert and item insert ReadMe Instructions:

Step 1: AWS CLI installation and Pre-requiste:
	1. Instal Amazon CLI for windows[ Refer https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html]
	2. Configuring Access Key and Security key and the profile setup [ Refer https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-quickstart.html#cli-configure-quickstart-config]
		Sample:
	 		$ aws configure
			AWS Access Key ID [None]: AKIAIOSFODNN7EXAMPLE
			AWS Secret Access Key [None]: wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY
			Default region name [None]: us-west-2
			Default output format [None]: json

	3.Enviornment variable setup [ Refer: https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-envvars.html]
	4.Enables the auto-prompt for the AWS CLI version. aws_cli_auto_prompt=on

