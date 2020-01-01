# Withings-API-Exporter

* SpringBoot
* PostgreSql
* Raspbian Lite as host OS

## Model

* Withings Heart Get
* Withings Heart List

Withings documentation: https://developer.withings.com/oauth2/#tag/heart

## Services

* Data persistence
* Token refresh
* Job scheduler
* Extended logging

Non included services are:
    - Automated database backup
    - Automated backup duplication to dropbox cloud storage

## Purpose of this project

Creating historical database for analytical and visualization purposes from ecg data.
It can be shown to doctors for diagnostical purposes.

