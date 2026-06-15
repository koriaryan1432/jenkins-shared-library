 // vars/deployApp.groovy                                                                                                                    
    def call(Map config = [:]) {                                                                                                                
        // Expected config parameters:                                                                                                          
        // - composeFile: path to docker-compose.yml                                                                                            
        // - buildTag: tag of images to run                                                                                                     
                                                                                                                                                
        echo "🔄 Deploying ShopEase container stack (Tag: ${config.buildTag})..."                                                               
                                                                                                                                                
        // Set the build tag in environment context for docker compose to pick up                                                               
        withEnv(["BUILD_TAG=${config.buildTag}"]) {                                                                                             
            if (isUnix()) {                                                                                                                     
                sh "docker compose -f ${config.composeFile} down"                                                                               
                sh "docker compose -f ${config.composeFile} up -d"                                                                              
            } else {                                                                                                                            
                bat "docker compose -f ${config.composeFile} down"                                                                              
                bat "docker compose -f ${config.composeFile} up -d"                                                                             
            }                                                                                                                                   
        }                                                                                                                                       
        echo "🎉 ShopEase deployment complete!"                                                                                                 
    }         
