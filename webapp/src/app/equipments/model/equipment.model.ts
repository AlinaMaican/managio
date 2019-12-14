import {MabecCode} from "./mabec-code.model";
import {ProtectionType} from "./protection-type.model";

export class EquipmentModel {
  constructor(public id: number,
              public name: string,
              public code: string,
              public mabecCode: MabecCode,
              public protectionType: ProtectionType,
              public size: string,
              public sex: string,
              public startDate: Date,
              public endDate: Date,
              public expirationDate:Date,
              public isChecked: boolean = false
  ) {}
}
