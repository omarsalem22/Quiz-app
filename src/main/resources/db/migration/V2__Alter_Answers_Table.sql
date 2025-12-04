ALTER TABLE answers
    ALTER COLUMN selected_option
    TYPE INTEGER
    USING selected_option::integer;