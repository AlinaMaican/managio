import {Employee} from "../employees/employee.model";
import {EquipmentModel} from "../../../../routing/webapp/src/app/equipments/model/equipment.model";

export class EmployeeEquipmentModel {
  constructor(public id: number,
              public employee: Employee,
              public equipment: EquipmentModel,
              public startDate: Date,
              public endDate: Date
              ) {
  }
}
