# Task Manager Web API

Use a RESTful task manager to:
- create, view, update, and delete tasks
- persist data using a cloud database (RDS and DynamoDB; with H2 for local testing)
- Deploy to EC2 / Elastic Beanstalk

---
- The password will need to be recreated when the RDS db instance is configured. 
- Add the env variables for the app in the ElasticBeanstalk/Dynamodb configurations
  - Ensure the active profile env var is also added: `SPRING_PROFILES_ACTIVE=<rds/dynamodb>`
- RDS code uses JPA and mySQL
- DynamoDb uses AWS SDK v2 to talk to DynamoDb
  Configuring roles:
  Eb needs 2 IAM roles when creating a new app:
- service role (EB service role): allows EB to manage resource on your behalf (autoscale, log, provision)
- instance profile (IAM role for EC2): Role is attached to teh Ec2 instance that runs the app. IT's used to access services like S3, DynamoDb, RDS, and CloudWatch

Service roles needed:
`AWSElasticBeanstalkManagedUpdatesCustomerRolePolicy`
`AWSElasticBeanstalkService`

instance profles needed:
`AWSElasticBeanstalkWebTier`
`AmazonDynamoDBFullAccess`
`CloudWatchLogsFullAccess`
`AmazonRDSFullAccess`


An ElasticBeanstalk application is needed (container for app)
An EB Environment is also needed inside the application -- this is where the app actually runs on EC2

Configuring RDS:
- ensure public access is granted (to allow inbound traffic from EC2)
- Will need a new security group with proper permissions

Notes:

ElasticBeanstalk is an aws managed compute service that allows you to easily deploy and manage applications.
One can migrate existing monolithic applications by "lift and shift" (without breaking apart or re-architecting code). 
Organizations new to AWS don't need deep cloud expertise to deploy apps to EB. Jsut uplaod the code (via .jar) and EB will provision, 
scale, lb and monitor health. 

EB uses core AWS services like EC2, ECS, Auto scaling, and ELB. 

Eb is a PaaS and sbastracts away infrastructure provisioning by:
- spinning up ec2 instances,
- creates an autoscaling group
- sets up ELB if needed
- manages env vas, stacks, logs, etc...
the user does not worry about infra level set up, only their app. 

EB still runs on EC2 but manages for you
- EB provisions EC under the hood
- you can ssh into instance if needed

EB automates and archastrates the EC2, LB, and ASG

EB provides (java, Node, py, docker)
- AWS handles the patching and updates to stack (not app)
EB is not serverless like lambda -- it's still Vm based, but manageed by AWS
User still pays for EC2, LB, db, and other infra used.