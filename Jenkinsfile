pipeline {
  agent none
  stages {
    stage('Jenkinsfile Test') {
      agent { label 'master' }
      steps {
        sh 'echo "Hello World"'
        sh 'mvn test'
      }
    }
  }
}
