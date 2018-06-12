import { Doctor } from "./doctor";
import { Symptom } from "./symptom";
import { Disease } from "./disease";
import { Prescription } from "./prescription";

export class Examination {

    constructor(
        public id: number,
        public date: any,
        public doctor: Doctor,
        public disease: Disease,
        public prescription: Prescription,
        public symptoms: Symptom[]
    ) {}
}
