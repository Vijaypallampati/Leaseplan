pipeline {
    agent any
    stages {
        stage("Verify Tooling!!"){
          steps{
                bat '''docker version'''
                echo 'Build Number : ' + env.BUILD_NUMBER
          }
        }

        stage("Prune Docker data!!"){
            steps{
                bat '''docker system prune -a --volumes -f'''
            }
        }

        stage("Build!!"){
            steps{
                echo 'Building the application'
                bat '''docker-compose build'''
                bat 'docker network create test'
            }
        }

        stage("Tests"){
            parallel{
                stage("GUI Test!!"){
                    steps{
                        echo 'Executing GUI test!!'
                        bat '''docker-compose up gui-test-suite'''
                    }
                }

                stage("API Test!!"){
                    steps{
                        echo 'Executing API test!!'
                        bat '''docker-compose up api-test-suite'''
                    }
                }
            }
        }
    }
    post {
        always{
            bat 'npm install -g cucumber-json-merge'
            bat 'cucumber-json-merge --dir report/'+ env.BUILD_NUMBER +'/ --recursive --out report/'+ env.BUILD_NUMBER +'/cucumber.json'
            cucumber 'report/'+ env.BUILD_NUMBER +'/cucumber.json'
        }
    }
}
