import React, { useState } from 'react';
import '../Styles/ResetPassword.css';

function ResetPassword() {
  const [email, setEmail] = useState('');
  const [otp, setOtp] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [otpSent, setOtpSent] = useState(false);

  // Function to simulate sending OTP
  const handleSendOtp = (e) => {
    e.preventDefault();

    if (!email.includes('@')) {
      setError('Please enter a valid email address.');
      setSuccess('');
      return;
    }

    // Simulate OTP sending
    setOtpSent(true);
    setError('');
    setSuccess('OTP sent to your email.');
  };

  // Function to handle password reset with OTP
  const handleResetPassword = (e) => {
    e.preventDefault();

    if (otp !== '1234') {
      setError('Invalid OTP.');
      setSuccess('');
      return;
    }

    if (newPassword.length < 6) {
      setError('Password must be at least 6 characters long.');
      setSuccess('');
      return;
    }

    setError('');
    setSuccess('Password has been successfully reset.');
  };

  return (
    <div className="reset-password-container">
      <h2>Reset Password</h2>
      {!otpSent ? (
        <form onSubmit={handleSendOtp} className="reset-password-form">
          <label>Email</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            placeholder="Enter your email"
          />
          <button type="submit">Send OTP</button>
        </form>
      ) : (
        <form onSubmit={handleResetPassword} className="reset-password-form">
          <label>OTP</label>
          <input
            type="text"
            value={otp}
            onChange={(e) => setOtp(e.target.value)}
            required
            placeholder="Enter OTP"
          />
          <label>New Password</label>
          <input
            type="password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            required
            placeholder="Enter new password"
          />
          <button type="submit">Reset Password</button>
        </form>
      )}

      {error && <p className="error-message">{error}</p>}
      {success && <p className="success-message">{success}</p>}
    </div>
  );
}

export default ResetPassword;
