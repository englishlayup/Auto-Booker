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
                bat 'scp ubuntu@52.1.75.25'
                bat 'mvn deploy'
            }
        }
    }
}