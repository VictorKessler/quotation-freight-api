#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE SCHEMA quotation_freight;
    ALTER USER "$POSTGRES_USER" SET search_path TO 'quotation_freight';
EOSQL