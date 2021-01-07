
import { WorkflowType } from '../wf-models/workflowtype';
import { Company } from './company';
import { Department, MenuItem, User, DashboardCube } from '../ui-models';

export class UserGeneralData {
	currentUser: User = new User;

}

export class DashboardGeneralData {
  totalColumns: number = 0;
	totalRows: number = 0;
	dashboardMenus: DashboardCube[][] = [];

}

export class AppGeneralData {
	menus: MenuItem[] = [];
	dashboard: DashboardGeneralData = new DashboardGeneralData;

}

export class CompanyGeneralData {
	departments: Department[] = [];
	users: User[] = [];
	company: Company = new Company;

}

export class WorkflowGeneralData {
	workflowTypes: WorkflowType[] = [];

}

export class GeneralData {
	isLogged: boolean=false;
	workflow: WorkflowGeneralData = new WorkflowGeneralData;
	company: CompanyGeneralData = new CompanyGeneralData;
	app: AppGeneralData = new AppGeneralData;
	user: UserGeneralData = new UserGeneralData;
	refreshToken: string = "";
	currentUserId : string = "";
	companyId : string = "";

}

