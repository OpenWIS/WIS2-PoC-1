import { Country } from "./Country";
import { DataFormat } from "./DataFormat";
import { WmoCode } from "./WmoCode";


export interface DataSet {
    id: number,
    name: string,
    description: string,
    state: string,
    city: string,
    country: Country,
    dataformat: DataFormat,
    latitude: string,
    longitude: string,
    elevation: string,
    references: string,
    license: string,
    relativeUrl: string,
    filenameprefix: string,
    jsonLd: string,
    imageUrl: string,
    downloadLink: string,
    subscriptionUri: string;
    awsQueue: string
    rdshDissEnabled: string,
    measurementUnit: string,
    periodFrom: Date,
    periodTo: Date,
    wmoCodes: WmoCode[]
    sendData: boolean;
    lastUpdate: Date;
  };