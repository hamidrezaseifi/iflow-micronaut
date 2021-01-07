
import { AbstractControl } from '@angular/forms';


export function parseDate(input :string, format :string) :Date {
	if(input === null || input === undefined){
		return new Date;
	}
	format = format || 'yyyy-mm-dd'; // default format
	var stringParts = input.match(/(\d+)/g);
	var i :number = 0, y =0, m = 0, d = 0;
	//var fmt = {'yyyy':0, "mm":0, "dd":0};
	var parts: number[] = [];

	if(stringParts){
    for(var o in stringParts){
      if(stringParts[o]){
        parts.push(parseInt(stringParts[o]));
      }
    }
	}

	//var parts = [Number(<string>stringParts[0]), Number(<string>stringParts[1]), Number(<string>stringParts[2]), ];
	format.replace(/(yyyy|dd|mm)/g, function(part) {
	  if(part = "yyyy"){
	    y = i++;
	  }
	  if(part = "mm"){
	    m = i++;
	  }
	  if(part = "dd"){
	    d = i++;
	  }
	  //fmt[part] = i++;
	  return part;
	});

	return new Date(parts[y], parts[m]-1, parts[d]);
}


export function formatDate(input :Date, format :string) :string {
	format = format || 'yyyy-mm-dd'; // default format
	var stringDate = format;

  stringDate = stringDate.replace(/(yyyy|dd|mm)/g, function(part) {
		if(part === 'yyyy'){
			return input.getFullYear() + "";
		}
		if(part === 'dd'){
			return getStringWithOneLeadingZero(input.getDate());
		}
		if(part === 'mm'){
			return getStringWithOneLeadingZero(input.getMonth() + 1);
		}
		return "";
	});

	return stringDate;
}

export function getStringWithOneLeadingZero(input :number) :string {
	return (input < 10 ? '0' : '') + input + "";
}


