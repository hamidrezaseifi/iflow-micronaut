import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

import { AuthenticationService } from '../services';
import { User, MenuItem } from '../ui-models';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css'],
})
export class TopBarComponent implements OnInit {

	menus: MenuItem[] = [];
	@Input('currentUser') currentUser: User;
	@Input('isLogged') isLogged: boolean;
	@Input('isPresent') isPresent : boolean;

	@Input('menus')
	set setMenus(_menus: MenuItem[]) {

	    if(Array.isArray(_menus) == false){
	      return;
	    }

	    this.menus = _menus;

	    for(var index in this.menus){
			  this.setMenuLabel(this.menus[index]);
		  }
	}

	@Output() loggingOut = new EventEmitter<boolean>();
	@Output() togglingPresens = new EventEmitter<boolean>();

	constructor(
		    private router: Router,
		    private translate: TranslateService,
		) {

 	}

	ngOnInit() {

	}

	setMenuLabel(menu:MenuItem) {

		this.translate.get(menu.id).subscribe((res: string) => {
			menu.label = res;
	    });

		for(var index in menu.children){
			this.setMenuLabel(menu.children[index]);
		}
	}

	logout(){
		this.loggingOut.emit(true);

	}

	togglePresens(){
	  this.togglingPresens.emit(true);
	}

	showProfile(){

	}

}
