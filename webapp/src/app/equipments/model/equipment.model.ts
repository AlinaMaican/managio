import {MabecCode} from "./mabec-code.model";

export class EquipmentModel {
  constructor(public id: number,
              public name: string,
              public code: string,
              public mabecCode: MabecCode,
              public protectionType: string,
              public size: string,
              public sex: string,
              public startDate: string,
              public endDate: string
  ) {}
}
