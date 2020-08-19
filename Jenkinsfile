pipeline {
    agent any

    tools {
        maven 'default'
    }

    stages {
        stage ('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage ('Test') {

            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    }
                }
        }

        stage ('Deploy') {
            steps {
                bat 'mvn deploy'
            }
        }
    }
}