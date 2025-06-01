pipeline {
    agent any
    
    parameters {
        string(name: 'Branch', defaultValue: '', description: 'Provide branch name')
    }
    
    environment  {
        scannerHome = tool 'SonarQubeScanner'
        projectKey = 'sonarqube-token'
}
    
    tools {
        maven 'Maven'   // Name must match Global Tool Config in Jenkins
    }

    stages {
        stage('Validate') {
            steps {
                script {
                    if (!params.Branch.startsWith('release-')) {
                        error "Invalid Branch: '${params.Branch}'. Branch name must start with 'release-'"
                    }
                    echo "Validation Success!"
                }
                git branch: params.Branch, url: 'https://github.com/pravingr/Devops-AWS-CICDUsingJenkins.git/'
            }
        }    
        stage('Build') {
            steps {
                echo "Building..${params.Branch}"
                sh 'mvn clean install'
            }
        }
        stage('QA') {
            steps {
                echo 'Sonar Test..'
                withSonarQubeEnv(credentialsId: 'sonarqube-token') {
                        sh """
                            ${scannerHome}/bin/sonar-scanner \
                            -Dsonar.projectKey=${projectKey} \
                            -Dsonar.sources=. \
                            """
                }

            }
        }
        stage('Realease') {
            steps {
                echo 'Storing Artifacts....'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
    
    post { 
        always { 
            echo 'Finished!'
        }
    }
}
