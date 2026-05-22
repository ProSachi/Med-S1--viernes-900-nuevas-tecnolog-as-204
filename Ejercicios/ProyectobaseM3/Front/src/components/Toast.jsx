import { useEffect } from 'react';

export default function Toast({ message, type = 'success', onClose, duration = 4000 }) {
  useEffect(() => {
    const timer = setTimeout(() => {
      onClose();
    }, duration);

    return () => clearTimeout(timer);
  }, [onClose, duration]);

  const icon = type === 'success' ? '✅' : '❌';

  return (
    <div className={`toast-container toast-${type}`}>
      <span className="toast-icon">{icon}</span>
      <span className="toast-message">{message}</span>
      <button className="toast-close" onClick={onClose}>&times;</button>
    </div>
  );
}
