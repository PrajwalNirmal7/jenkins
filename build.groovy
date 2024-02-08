pipeline {
    agent { 
        label 'node' 
        }
    stages {
        stage('Clonig the Git Repository') {
            steps {
                echo 'clone repositery consist of build packages'
                git branch: 'main', url: 'https://github.com/Shoaibraj/CFT-cloudformation.git'
                  }
             }
        stage('Installation of Aws Cli') {
            steps {
                sh '''
                echo "installing aws cli"
                sudo apt update
                sudo apt install awscli -y 
                '''
            }
        }     
        stage('CloudFormation On Cloud') 
        {
            steps {
            sh '''
            echo "CFT Run"
            cd /home/ubuntu/workspace/project-1
            aws cloudformation create-stack --stack-name mystacknew --template-body file://public.yaml --parameters ParameterKey=KeyPairName,ParameterValue=node.js --region us-east-1
            '''
            }
        }
    }
}