import { Component, OnInit, Input } from '@angular/core';
import { LoadingServiceService } from '../../services/loading-service.service';

@Component({
  selector: 'app-loading-dialog',
  templateUrl: './loading-dialog.component.html',
  styleUrls: ['./loading-dialog.component.css']
})
export class LoadingDialogComponent implements OnInit {

	_showLoading : boolean = false;

	showLoading :boolean = false;
	
	constructor(private loadingService: LoadingServiceService,) { }

	ngOnInit() {
		this.loadingService.loadingSubject.subscribe((data : boolean) => {
			this.showLoading = data;
		});
	}

}
