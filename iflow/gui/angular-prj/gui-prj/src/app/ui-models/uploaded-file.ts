
import { OcrWord, OcrBox } from './ocr-word';
import { UploadedResult } from './uploaded-result';

export class UploadedFile {

	fileName: string = "";
	scanedPdfPath: string = "";
	scanedHocrPath: string = "";
	fileIsPdf: boolean = false;
	fileIsImage: boolean = false;
	imageSizeX: number = 0;
	imageSizeY: number = 0;
	uploadResult: UploadedResult = new UploadedResult;

	foundWords: OcrWord[] = [];

	isScanned: boolean = false;

}
