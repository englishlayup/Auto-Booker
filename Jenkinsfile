pipeline {
    agent any

    tools {
        maven 'default'
        jdk 'jdk8'
    }

    stages {
        stage ('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage ('Deploy') {
            steps {
                bat 'mvn deploy -DskipTests=false'
            }
        }
    }
}