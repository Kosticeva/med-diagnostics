import { Drug } from "./drug";

export class Prescription {
    constructor(
        public id: number,
        public plan: string,
        public drug: Drug
    ) {}
}
