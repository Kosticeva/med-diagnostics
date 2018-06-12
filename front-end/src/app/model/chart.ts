import { Patient } from "./patient";
import { Examination } from "./examination";

export class Chart {

    constructor(
        public id: number,
        public patient: Patient,
        public examinations: Examination[]
    ) {}
}
