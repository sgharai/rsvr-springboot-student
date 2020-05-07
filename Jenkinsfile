pipeline {
  agent none
  stages {
    stage('Jenkinsfile Test') {
      agent { label 'master' }
      steps {
        sh 'mvn test'
      }
    }
  }
}
