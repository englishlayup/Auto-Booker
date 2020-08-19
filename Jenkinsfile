pipeline {
    agent any

    tools {
        maven '3.5.0'
        jdk 'jdk8'
    }

    stages {
        stage ('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage ('Test') {

            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    }
                }
        }

        stage ('Deploy') {
            steps {
                sh 'mvn deploy'
            }
        }
    }
}