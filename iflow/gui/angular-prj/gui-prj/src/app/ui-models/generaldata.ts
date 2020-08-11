
import { WorkflowType } from '../wf-models/workflowtype';
import { Company } from './company';
import { Department, MenuItem, User, DashboardCube } from '../ui-models';
 
export interface UserGeneralData {
	currentUser: User;
	
}

export interface DashboardGeneralData {
  totalColumns: number;
	totalRows: number;
	dashboardMenus: DashboardCube[][];
	
}

export interface AppGeneralData {
	menus: MenuItem[];
	dashboard: DashboardGeneralData;
	
}

export interface CompanyGeneralData {
	departments: Department[];
	users: User[];
	company: Company;
	
}

export interface WorkflowGeneralData {
	worlflowTypes: WorkflowType[];
	
}

export class GeneralData {
	isLogged: boolean;
	workflow: WorkflowGeneralData;
	company: CompanyGeneralData;
	app: AppGeneralData;
	user: UserGeneralData;

}

