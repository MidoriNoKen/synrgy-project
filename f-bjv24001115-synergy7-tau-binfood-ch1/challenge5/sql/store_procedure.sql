-- Create stored procedure for reading all users
CREATE OR REPLACE FUNCTION read_all_users()
RETURNS TABLE (
    user_id BIGINT,
    username VARCHAR,
    email_address VARCHAR,
    password VARCHAR
) AS $$
BEGIN
    RETURN QUERY
    SELECT u.user_id, u.username, u.email_address, u.password
    FROM Users u;
END;
$$ LANGUAGE plpgsql;

-- Create stored procedure for reading a user by ID
CREATE OR REPLACE FUNCTION read_user(
    p_user_id BIGINT
) RETURNS TABLE (
    user_id BIGINT,
    username VARCHAR,
    email_address VARCHAR,
    password VARCHAR
) AS $$
BEGIN	
    RETURN QUERY
    SELECT u.user_id, u.username, u.email_address, u.password
    FROM Users u
    WHERE u.user_id = p_user_id;
END;
$$ LANGUAGE plpgsql;

-- Create stored procedure for inserting a new user
CREATE OR REPLACE FUNCTION create_user(
    p_username VARCHAR,
    p_email_address VARCHAR,
    p_password VARCHAR
) RETURNS VARCHAR AS $$
DECLARE
    result_message VARCHAR;
BEGIN
    BEGIN
        INSERT INTO Users (username, email_address, password)
        VALUES (p_username, p_email_address, p_password);
        result_message := 'User created successfully.';
    EXCEPTION WHEN others THEN
        result_message := 'Failed to create user: ' || SQLERRM;
    END;
    RETURN result_message;
END;
$$ LANGUAGE plpgsql;

-- Create stored procedure for updating a user
CREATE OR REPLACE FUNCTION update_user(
    p_user_id BIGINT,
    p_username VARCHAR,
    p_email_address VARCHAR,
    p_password VARCHAR
) RETURNS VARCHAR AS $$
BEGIN
    UPDATE Users
    SET username = p_username,
        email_address = p_email_address,
        password = p_password
    WHERE user_id = p_user_id;
    
    IF FOUND THEN
        RETURN 'User updated successfully';
    ELSE
        RETURN 'User with given ID not found';
    END IF;
END;
$$ LANGUAGE plpgsql;


-- Create stored procedure for deleting a user
CREATE OR REPLACE FUNCTION delete_user(
    p_user_id BIGINT
) RETURNS VOID AS $$
BEGIN
    DELETE FROM Users
    WHERE user_id = p_user_id;
END;
$$ LANGUAGE plpgsql;
