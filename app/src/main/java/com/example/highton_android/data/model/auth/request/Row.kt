package com.example.highton_android.data.model.auth.request

import com.google.gson.annotations.SerializedName

data class Row(
    @SerializedName("ATPT_OFCDC_SC_CODE")
    val aTPTOFCDCSCCODE: String?,
    @SerializedName("ATPT_OFCDC_SC_NM")
    val aTPTOFCDCSCNM: String?,
    @SerializedName("COEDU_SC_NM")
    val cOEDUSCNM: String?,
    @SerializedName("DGHT_SC_NM")
    val dGHTSCNM: String?,
    @SerializedName("ENE_BFE_SEHF_SC_NM")
    val eNEBFESEHFSCNM: String?,
    @SerializedName("ENG_SCHUL_NM")
    val eNGSCHULNM: String?,
    @SerializedName("FOAS_MEMRD")
    val fOASMEMRD: String?,
    @SerializedName("FOND_SC_NM")
    val fONDSCNM: String?,
    @SerializedName("FOND_YMD")
    val fONDYMD: String?,
    @SerializedName("HMPG_ADRES")
    val hMPGADRES: String?,
    @SerializedName("HS_GNRL_BUSNS_SC_NM")
    val hSGNRLBUSNSSCNM: String?,
    @SerializedName("HS_SC_NM")
    val hSSCNM: Any?,
    @SerializedName("INDST_SPECL_CCCCL_EXST_YN")
    val iNDSTSPECLCCCCLEXSTYN: String?,
    @SerializedName("JU_ORG_NM")
    val jUORGNM: String?,
    @SerializedName("LCTN_SC_NM")
    val lCTNSCNM: String?,
    @SerializedName("LOAD_DTM")
    val lOADDTM: String?,
    @SerializedName("ORG_FAXNO")
    val oRGFAXNO: String?,
    @SerializedName("ORG_RDNDA")
    val oRGRDNDA: String?,
    @SerializedName("ORG_RDNMA")
    val oRGRDNMA: String?,
    @SerializedName("ORG_RDNZC")
    val oRGRDNZC: String?,
    @SerializedName("ORG_TELNO")
    val oRGTELNO: String?,
    @SerializedName("SCHUL_KND_SC_NM")
    val sCHULKNDSCNM: String?,
    @SerializedName("SCHUL_NM")
    val sCHULNM: String?,
    @SerializedName("SD_SCHUL_CODE")
    val sDSCHULCODE: String?,
    @SerializedName("SPCLY_PURPS_HS_ORD_NM")
    val sPCLYPURPSHSORDNM: Any?
)