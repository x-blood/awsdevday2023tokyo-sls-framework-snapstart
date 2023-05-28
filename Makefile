STACK_NAME=awsdevday2023tokyo-sls-framework-snapstart

build:
	mvn clean package

sam-deploy-guided:
	sam deploy --guided --stack-name ${STACK_NAME}

sam-deploy:
	sam deploy
