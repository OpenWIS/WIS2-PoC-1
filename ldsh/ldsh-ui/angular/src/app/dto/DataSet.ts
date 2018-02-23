import { Country } from "./Country";
import { DataFormat } from "./DataFormat";
import { WmoCode } from "./WmoCode";


export interface DataSet {
    id: number,
    name: String,
    description: String,
    state: String,
    city: String,
    country: Country,
    dataformat: DataFormat,
    latitude: String,
    longitude: String,
    elevation: String,
    references: String,
    license: String,
    relativeUrl: String,
    filenameprefix: String,
    jsonLd: String,
    imageUrl: String,
    downloadLink: String,
    subscriptionUri: String;
    awsQueue: String
    rdshDissEnabled: String,
    measurementUnit: String,
    periodFrom: Date,
    periodTo: Date,
    wmoCodes: WmoCode[]
    sendData: boolean;
    lastUpdate: Date;
  };