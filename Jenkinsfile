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
                bat 'sshPublisher(publishers: [sshPublisherDesc(configName: 'Ubuntu-EC2', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '\'artifact\'yyyy-MM-dd', remoteDirectorySDF: false, removePrefix: 'target', sourceFiles: 'target/*.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])'
            }
        }
    }
}