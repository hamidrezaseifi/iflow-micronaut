
import { CompanyWorkflowtypeItemOcrSettingPresetItem } from './company-workflowtyp-eitem-ocr-setting-preset-item';

export class CompanyWorkflowtypeItemOcrSettingPreset {
	identity: string = "";
	companyIdentity: string = "";
	workflowTypeIdentity: string = "";
	presetName: string = "";
	status: number = 1;
	version: number = 1;
	items: CompanyWorkflowtypeItemOcrSettingPresetItem[] = [];
  	
}
