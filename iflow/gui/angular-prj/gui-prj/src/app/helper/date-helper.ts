
import { AbstractControl } from '@angular/forms';


export function parseDate(input :string, format :string) :Date {
	if(input === null || input === undefined){
		return null;
	}
	format = format || 'yyyy-mm-dd'; // default format
	var stringParts = input.match(/(\d+)/g), i = 0, fmt = {};
	var parts = [Number(stringParts[0]), Number(stringParts[1]), Number(stringParts[2]), ];
	format.replace(/(yyyy|dd|mm)/g, function(part) { fmt[part] = i++; return part; });

	return new Date(parts[fmt['yyyy']], parts[fmt['mm']]-1, parts[fmt['dd']]);
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
	});

	return stringDate;
}

export function getStringWithOneLeadingZero(input :number) :string {
	return (input < 10 ? '0' : '') + input + "";
}
 

