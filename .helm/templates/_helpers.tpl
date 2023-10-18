{{/* vim: set filetype=mustache: */}}
{{/*
Expand the name of the chart.
*/}}
{{- define "atlas-api.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "atlas-api.fullname" -}}
{{- if .Values.fullnameOverride -}}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- $name := default .Chart.Name .Values.nameOverride -}}
{{- if contains $name .Release.Name -}}
{{- .Release.Name | trunc 63 | trimSuffix "-" -}}
{{- else -}}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" -}}
{{- end -}}
{{- end -}}
{{- end -}}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "atlas-api.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{/*
Common labels
*/}}
{{- define "atlas-api.labels" -}}
helm.sh/chart: {{ include "atlas-api.chart" . }}
{{ include "atlas-api.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end -}}

{{/*
Selector labels
*/}}
{{- define "atlas-api.selectorLabels" -}}
app.kubernetes.io/name: {{ include "atlas-api.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{/*
Create the name of the service account to use
*/}}
{{- define "atlas-api.serviceAccountName" -}}
{{- if .Values.serviceAccount.create -}}
    {{ default (include "atlas-api.fullname" .) .Values.serviceAccount.name }}
{{- else -}}
    {{ default "default" .Values.serviceAccount.name }}
{{- end -}}
{{- end -}}

{{/*
Create the database secret name
*/}}
{{- define "atlas-api.db-secret" -}}
{{- if empty .Values.database.existingSecret }}
{{- printf "%s-db" (include "atlas-api.fullname" .) }}
{{- else }}
{{ default "default" .Values.database.existingSecret}}
{{- end }}
{{- end -}}

{{/*
Create the application.properties name
*/}}
{{- define "atlas-api.config" -}}
{{- if empty .Values.existingConfig }}
{{- printf "%s-config" (include "atlas-api.fullname" .) }}
{{- else }}
{{ default "default" .Values.database.existingConfig}}
{{- end }}
{{- end -}}