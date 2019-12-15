CREATE SCHEMA IF NOT EXISTS withings;

CREATE TABLE IF NOT EXISTS withings.heartlist {
    heartlist_id INT PRIMARY KEY,
    device_id INT,
    model INT,
    signal_id INT,
    diastole INT,
    systole INT,
    heart_rate INT,
    call_timestamp INT,
    call_startdate Date,
    call_enddate Date,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
}

CREATE INDEX IF NOT EXISTS signal_id_list_idx ON withings.heartlist (signal_id);
CREATE INDEX IF NOT EXISTS call_startdate_idx ON withings.heartlist (call_startdate);
CREATE INDEX IF NOT EXISTS call_enddate_idx ON withings.heartlist (call_enddate);

CREATE TABLE IF NOT EXISTS withings.heartget {
    heartget_id INT PRIMARY KEY,
    signal_id INT,
    signal INT,
    sampling_frequency INT,
    wearposition INT,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
}

CREATE INDEX IF NOT EXISTS signal_id_get_idx ON withings.heartlist (signal_id);

