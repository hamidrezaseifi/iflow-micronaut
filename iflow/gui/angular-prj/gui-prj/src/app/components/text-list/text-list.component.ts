import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-text-list',
  templateUrl: './text-list.component.html',
  styleUrls: ['./text-list.component.css']
})
export class TextListComponent implements OnInit {

	_text :string = "";
	@Input('text')
	set text(_value:string) {
	    this._text = _value.trim();
	    if(this._text === "" || this._text === null){
	      this.textList = [];
	    }
	    else{
	      this.textList = this._text.split(this.separator);
	    }
	    
	}
	
	@Output("textChanged") onTextChanged = new EventEmitter<string>();
	
	
	@Input('separator') separator : string = ";";

	textList: string[] = [];
	
	textToAdd :string = "";
	constructor() { }

	ngOnInit() { }
	
	deleteText(deletingText:string){
		
		var temp =  this.textList.filter(function(txt) {
			return txt !== deletingText;
		});
		
		this.textList = temp;
		this.rebuildText();
		
	}
	
	addText(){
		
		this.textToAdd = this.textToAdd.trim();
		
		if(this.textToAdd === ''){
			return;
		}
		
		this.deleteText(this.textToAdd);
		
		this.textList.push(this.textToAdd);
		
		this.textToAdd = '';
		
		this.rebuildText();
		
	}
	
	addTextChanged(addingText:string){
		
		this.textToAdd = addingText;
	}
	

	private rebuildText(){
		this._text = "";
				
		for(var index in this.textList){
			this._text += this.textList[index] + this.separator;
		}
		if(this._text.endsWith(";")){
			this._text = this._text.slice(0, this._text.length - 1);
		}
		
		this.onTextChanged.emit(this._text);
	}
}
