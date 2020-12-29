import { MenuItem } from './menuitem';

export class DashboardCube {
  menu: MenuItem = null;
  appId: string = "";
	menuId: string = "";
	text: string = "";
	hasMenu: boolean = false;
	//image: string = "";
  //backColor: string = "";
  //foreColor: string = "";
	//style: string = "";

	//url: string = "";
  row: number = 0;
  column: number = 0;
  status: number = 0;
  version: number = 0;

	hasImage() : boolean{
	  return this.menu != null && this.menu.image != null && this.menu.image != '';
	}
}

