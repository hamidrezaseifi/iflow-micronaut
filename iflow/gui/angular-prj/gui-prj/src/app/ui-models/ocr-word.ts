
export class OcrWord {

	box :OcrBox = new OcrBox;
	isStrong: boolean = false;
	text: string = "";
	id: string = "";
	title: string = "";
	className: string = "";
	value :OcrWord = new OcrWord;
	pageIndex :number = 0;
	pageWidth :number = 0;
	pageHeight :number = 0;

}

export class OcrBox {
	left :number = 0;
	right :number = 0;
	top :number = 0;
	bottom :number = 0;
	width :number = 0;
	height :number = 0;
}
