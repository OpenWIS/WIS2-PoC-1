import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../environments/environment";
import { LdshDTO } from "../../_dto/Ldsh.dto";
import { RestClient } from "./rest-client.service";
import { Headers } from "@angular/http";

@Injectable()
export class LdshService {
  constructor(private restClient: RestClient) {}

  // Return all LDSHs registered in the system.
  list(successCallback, errorCallback): void {
    this.restClient
      .get(
        environment.CONSTANTS.API_ROOT + "/ldsh",
        null,
        successCallback,
        errorCallback
      )
      .subscribe();
  }

  // Fetch a specific LDSH by Id.
  get(ldshId: string, successCallback, errorCallback): void {
    this.restClient
      .get(
        environment.CONSTANTS.API_ROOT + "/ldsh/" + ldshId,
        null,
        successCallback,
        errorCallback
      )
      .subscribe();
  }

  // Save or create an LDSH.
  save(ldshDTO: LdshDTO, successCallback, errorCallback): void {
    this.restClient
      .post(
        environment.CONSTANTS.API_ROOT + "/ldsh",
        new Headers({ "Content-Type": "application/json" }),
        ldshDTO,
        successCallback,
        errorCallback
      )
      .subscribe();
  }

  // Delete an LDSH.
  delete(ldshId: string, successCallback, errorCallback): void {
    this.restClient
      .delete(
        environment.CONSTANTS.API_ROOT + "/ldsh/" + ldshId,
        null,
        successCallback,
        errorCallback
      )
      .subscribe();
  }

  // Index an LDSH
  index(ldshId: string, successCallback, errorCallback): void {
    this.restClient
      .get(
        environment.CONSTANTS.API_ROOT + "/ldsh/index/" + ldshId,
        null,
        successCallback,
        errorCallback
      )
      .subscribe();
  }

}
