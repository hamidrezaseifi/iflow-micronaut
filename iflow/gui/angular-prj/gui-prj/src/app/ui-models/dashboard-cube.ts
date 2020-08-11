
export class DashboardCube {
  appId: string = "";
	menuId: string = "";
	text: string = "";
	image: string = "";
  backColor: string = "";
  foreColor: string = "";
	style: string = "";

	url: string = "";
  row: number = 0;
  column: number = 0;
  status: number = 0;
  version: number = 0;
  	
	hasImage() : boolean{
	  return this.image != null && this.image != '';
	}
}

