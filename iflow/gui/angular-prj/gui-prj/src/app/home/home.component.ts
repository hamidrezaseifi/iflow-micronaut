import { Component, OnInit, Input} from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs';
import $ from "jquery";

import { GlobalService } from '../services/global.service';

import { DashboardCube, GeneralData, MenuItem } from '../ui-models';

@Component({
  selector: 'app-root',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
    

		isPresentObs :Observable<boolean> = null;

		isPresent : boolean = true;
		
		totalColumns: number = 10;
		totalRows: number = 6;

		cubes : DashboardCube[][] = [];
		
		menus: MenuItem[] = [];
		
		showSelectMenuDialog : boolean = false;
		showedSubMenu : any[] = [];
		selectedCube :DashboardCube = null;
		
		isEditMode = false;
		menusIsChanged = false;
	
    constructor(private global: GlobalService, 
        private route: ActivatedRoute,
        private router: Router ) {
      		this.isPresentObs = this.global.presensSubject.asObservable();
    }

    ngOnInit() {
      
      this.isPresentObs.subscribe((res: boolean) => {
      	this.isPresent = res;
      });
      
      this.global.currentSessionDataSubject.asObservable().subscribe((res: GeneralData) => {
      	this.menus = res.app.menus;
      	this.cubes = res.app.dashboard.dashboardMenus;
      	this.totalColumns = res.app.dashboard.totalColumns;
      	this.totalRows = res.app.dashboard.totalRows;
      });
      
      /*for(var r = 0; r < this.totalRows; r ++){
        var cubelist : DashboardCube[] = [];
        for(var c = 0; c < this.totalColumns; c ++){
          var cube : DashboardCube = new DashboardCube();
        	cube.text = "Cube " + r + "-" + c;
        	cube.url = "/#" + r + "-" + c;
        	cube.row = r;
        	cube.column = c;
        	cube.image = "/assets/images/no-image.png";
        
          cubelist.push(cube);
        }
        
        this.cubes.push(cubelist);
      }*/
      
    }
    
    getMenuItemTreeData(){
      
    }
    
    
    selectMenuItem(cube:DashboardCube){
      
      if(this.isEditMode === false){
        
        if(cube.url != null && cube.url != ''){
          this.router.navigate([cube.url]);  
        }
        
        
        return;
      }
      
      this.selectedCube = cube;
      this.showSelectMenuDialog = true;
    }
    
    showMenuDialog(){
      this.showSelectMenuDialog = true;
    }
    
    hideMenuDialog(){
      this.showSelectMenuDialog = false;
    }
    
    isSubMenuShowed(id: string):boolean{
      
      for(var index in this.showedSubMenu){
        if(this.showedSubMenu[index].id === id){
          
          return this.showedSubMenu[index].show;
        }
      }
      
      this.showedSubMenu.push({"id" : id, "show" : false});
          
      return false;
    }
    
    toggleSubMenuShowed(id: string):boolean{
      
      
      for(var index in this.showedSubMenu){
        if(this.showedSubMenu[index].id === id){
                 
          this.showedSubMenu[index].show = !this.showedSubMenu[index].show;
          return;
        }
      }
      
      this.showedSubMenu.push({"id" : id, "show" : false});
      
    }
    
    selectMenuItemForCube(menu){
      
      this.selectedCube.text = menu.label;
      this.selectedCube.image = menu.image;
      this.selectedCube.url = menu.url;
      
      this.menusIsChanged = true;
      
      this.hideMenuDialog();
    }
    
    startEditCubes(){
      this.isEditMode = this.isEditMode === false ? true : false;
    }
    
    applyEditCubes(){
      
      
      this.menusIsChanged = false;
      this.isEditMode = false;
    }
    
    

}