pipeline {
    agent any
    
    parameters {
        string(name: 'Branch', defaultValue: '', description: 'Provide branch name')
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
            }
        }    
        stage('Build') {
            steps {
                echo "Building..${params.Branch}"
            }
        }
        stage('QA') {
            steps {
                echo 'Sonar Test..'
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
