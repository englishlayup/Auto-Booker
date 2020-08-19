pipeline {
    agent any

    tools {
        maven 'default'
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