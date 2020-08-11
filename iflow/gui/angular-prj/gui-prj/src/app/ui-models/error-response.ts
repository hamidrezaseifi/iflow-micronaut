
export class ErrorResponse {
				
	status: any;
	errorType: string;
	message: string;
	details: string;
	moduleName: string;
  	
	constructor(response: any ) { 
		this.message = "Unknown response error!";
		this.details = "";
		
		if(response && response != null){
			if(response.status){
				if(response.status === 0){
					this.message = "Connection Error!";
					this.details = "";
				}
				else{
					if(response.error){
						
						if(response.error.message){
							this.message = response.error.message;
							this.details = "";
							if(response.error.details){
								this.details = response.error.details;
							}
						}
						else{
							if(response.message){
								this.message = response.message;
								this.details = "";
								
							}
							
						}
					}
				}
			}
		}
		
	}

}