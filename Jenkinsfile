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
                bat 'mvn deploy -DaltReleaseDeploymentRepository=""gh::default::https://maven.pkg.github.com/englishlayup/COSC4427FinalProject/" -DaltSnapshotDeploymentRepository=""gh::default::https://maven.pkg.github.com/englishlayup/COSC4427FinalProject/"'
                bat 'java -jar thatfilethatyoureceived'
            }
        }
    }
}