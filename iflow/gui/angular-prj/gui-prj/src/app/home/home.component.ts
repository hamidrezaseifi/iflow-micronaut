import { Component, OnInit, Input} from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from 'rxjs';
import $ from "jquery";

import { GlobalService } from '../services/global.service';

import { DashboardCube, GeneralData, MenuItem } from '../ui-models';

import { UserEditService } from '../services/company/user-edit.service';
import { ErrorServiceService } from '../services/error-service.service';
import { LoadingServiceService } from '../services/loading-service.service';
import { TranslateService } from '@ngx-translate/core';

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
        private router: Router,
        private editService :UserEditService,
        private loadingService: LoadingServiceService,
        protected translate: TranslateService,
        private errorService: ErrorServiceService) {
      		this.isPresentObs = this.global.presensSubject.asObservable();
    }

    ngOnInit() {

      this.isPresentObs.subscribe((res: boolean) => {
      	this.isPresent = res;
      });

      this.reloadCubes();

    }

    reloadCubes(){
      this.global.currentSessionDataSubject.asObservable().subscribe((res: GeneralData) => {
        if(res == null || res == undefined){
          return;
        }
      	this.menus = res.app.menus;
      	this.cubes = res.app.dashboard.dashboardMenus;
      	this.setCubesText();
      	console.log("read cubes: ", this.cubes);

      	this.totalColumns = res.app.dashboard.totalColumns;
      	this.totalRows = res.app.dashboard.totalRows;
      });
    }

    setCubesText(){

      for(var rowIndex in this.cubes){
        for(var cubeIndex in this.cubes[rowIndex]){
          var cube = this.cubes[rowIndex][cubeIndex];
          if(cube.menuId != undefined && cube.menuId != "" && cube.menuId != null){
            this.setCubeText(rowIndex, cubeIndex);
          }

        }
      }

    }

    setCubeText(rowIndex, cubeIndex){
      this.translate.get(this.cubes[rowIndex][cubeIndex].menuId).subscribe((res: string) => {
            		            	this.cubes[rowIndex][cubeIndex].text = res;
      });
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
      this.selectedCube.menuId = menu.id;

      this.menusIsChanged = true;

      this.hideMenuDialog();
    }

    deleteMenuItemForCube(){

      this.selectedCube.text = " ";
      this.selectedCube.image = "/assets/images/no-image.png";
      this.selectedCube.url = "";
      this.selectedCube.menuId = "";

      this.menusIsChanged = true;

      this.hideMenuDialog();
    }

    startEditCubes(){
      this.isEditMode = this.isEditMode === false ? true : false;
    }

    applyEditCubes(){

      this.loadingService.showLoading();

      this.editService.applyDashboardLinks(this.cubes).subscribe(
            (result) => {
                console.log("Create user result", result);
                this.menusIsChanged = false;
                this.isEditMode = false;
                this.global.loadAllSetting();
                this.reloadCubes();
            },
            response => {
              console.log("Error in create user", response);

              this.errorService.showErrorResponse(response);
              this.loadingService.hideLoading();
              this.menusIsChanged = false;
              this.isEditMode = false;
            },
            () => {

              this.loadingService.hideLoading();
            }
      );




    }



}
