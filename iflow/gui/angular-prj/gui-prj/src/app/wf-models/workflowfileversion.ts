
import { User } from '../ui-models';

export class WorkflowFileVersion {
	private createdBy :User = new User;
	private createdByIdentity :string = "";
	private filePath :string = "";
	private comments :string = "";
	private fileVersion :Number = 0;
	private status :Number = 0;

}

