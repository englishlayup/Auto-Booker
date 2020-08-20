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

        stage ('Deploy') {
            steps {
                bat 'scp  -i C:/Jenkins/AWS-Ubuntu-EC2.pem "C:/Users/Drink Water/.jenkins/workspace/COSC4427-Pipeline/target" ubuntu@52.1.75.25:/home/ubuntu/'
            }
        }
    }
}