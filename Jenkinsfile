pipeline {
    agent any

    tools {
        maven 'default'
        jdk 'jdk8'
    }

    stages {
        stage ('Build') {
            steps {
                bat 'mvn package'
            }
        }

        stage ('Publish') {
            steps {
                bat 'scp  -i C:/Jenkins/AWS-Ubuntu-EC2.pem "C:/Users/Drink Water/.jenkins/workspace/COSC4427-Pipeline/target/COSC4427FinalProject-1.0-SNAPSHOT-jar-with-dependencies.jar" ubuntu@52.1.75.25:/home/ubuntu/'
            }
        }

        stage ('Deploy') {
                    steps {
                        bat 'ssh  -i C:/Jenkins/AWS-Ubuntu-EC2.pem  ubuntu@52.1.75.25 java -jar /home/ubuntu/COSC4427FinalProject-1.0-SNAPSHOT-jar-with-dependencies.jar'
                    }
                }
    }
}